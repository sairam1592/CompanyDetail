package com.wiredelta.sample.companydetail.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wiredelta.sample.companydetail.Constants;
import com.wiredelta.sample.companydetail.R;
import com.wiredelta.sample.companydetail.model.Company;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Company> compDetails;
    private String[] dept;
    Spinner filter_spinner;
    SearchView search_view;
    ViewHolder.ContentAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        compDetails = getIntent().getParcelableArrayListExtra(Constants.INTENT_COMPDETAILS);

        dept = getResources().getStringArray(R.array.departments);
        onDeptSelection();
        search_view = (SearchView) findViewById(R.id.editText_search);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter = new ViewHolder.ContentAdapter(compDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        search_view.setOnQueryTextListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, InfoActivity.class));
            overridePendingTransition(R.transition.push_up_in, R.transition.push_up_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextChange(String query) {
            filterView(query, Constants.TAG_COMP_NAME);
            return true;
        }

        public boolean onQueryTextSubmit(String query) {
            return false;
        }
    };


    public void onDeptSelection() {
        filter_spinner = (Spinner) findViewById(R.id.FilterSpinner);
        ArrayAdapter<String> adapter_dept = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dept);
        adapter_dept
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_spinner.setAdapter(adapter_dept);
        filter_spinner.setOnItemSelectedListener(this);
    }

    public void onFilterClick(View view) {
        search_view.setVisibility(View.GONE);
        filter_spinner.setVisibility(View.VISIBLE);
    }

    public void onSearchClick(View view) {
        search_view.setVisibility(View.VISIBLE);
        filter_spinner.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.FilterSpinner:
                filterView(filter_spinner.getSelectedItem().toString(), Constants.TAG_COMP_DEPT);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void filterView(String value, String tag) {
        ArrayList<Company> filterCompDet = new ArrayList<>();
        String text;
        if (tag.equalsIgnoreCase(Constants.TAG_COMP_NAME)) {
            for (int i = 0; i < compDetails.size(); i++) {
                text = compDetails.get(i).getComapnyName();
                if (text.toLowerCase().contains(value.toLowerCase())) {
                    filterCompDet.add(compDetails.get(i));
                }
            }
        } else if (tag.equalsIgnoreCase(Constants.TAG_COMP_DEPT)) {
            for (int i = 0; i < compDetails.size(); i++) {
                text = compDetails.get(i).getCompanyDepartments();
                if (text.toLowerCase().contains(value.toLowerCase())) {
                    filterCompDet.add(compDetails.get(i));
                }
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new ViewHolder.ContentAdapter(filterCompDet);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button expand;
        boolean isExpand = true;
        RelativeLayout test;
        TextView compName, compId, compOwner, compDate, compDesc, compDept;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_cardview, parent, false));

            compId = (TextView) itemView.findViewById(R.id.textView_id);
            compName = (TextView) itemView.findViewById(R.id.textView_cname_label);
            compOwner = (TextView) itemView.findViewById(R.id.textView_cowner_value);
            compDate = (TextView) itemView.findViewById(R.id.text_startdate);
            compDesc = (TextView) itemView.findViewById(R.id.textView_desc_value);
            compDept = (TextView) itemView.findViewById(R.id.textView_dept_value);

            test = (RelativeLayout) itemView.findViewById(R.id.relativelayout_company_details);
            expand = (Button) itemView.findViewById(R.id.button_expand);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isExpand) {
                        test.setVisibility(View.VISIBLE);
                        expand.setBackgroundResource(R.drawable.up_arrow);
                    } else {
                        expand.setBackgroundResource(R.drawable.down_arrow);
                        test.setVisibility(View.GONE);
                    }
                    isExpand = !isExpand;
                }
            });
        }

        public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
            ArrayList<Company> compDetails;

            public ContentAdapter(ArrayList<Company> compDetails) {
                this.compDetails = compDetails;
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {

                holder.compId.setText(compDetails.get(position).getCompanyID());
                holder.compName.setText(compDetails.get(position).getComapnyName());
                holder.compOwner.setText(compDetails.get(position).getCompanyOwner());
                holder.compDate.setText(compDetails.get(position).getCompanyStartDate());
                holder.compDesc.setText(compDetails.get(position).getCompanyDescription());
                holder.compDept.setText(compDetails.get(position).getCompanyDepartments());

            }

            @Override
            public int getItemCount() {
                return compDetails.size();
            }
        }
    }

}
