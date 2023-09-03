package com.java.xiongzeen.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.xiongzeen.R;
import com.java.xiongzeen.service.NewsManager;

public class RecordListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsListAdapter listAdapter;
    private boolean mode = false; // 0 for history, 1 for favorite
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mode = getArguments().getBoolean("mode");
        }
        Log.d("RecordListFragment", "mode" + mode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_record_list, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.news_list);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setStackFromEnd(true);
        llm.setReverseLayout(true);
        recyclerView.setLayoutManager(llm);
        listAdapter = new NewsListAdapter(this, context, NewsManager.getInstance().getRecords(mode));
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        return view;
    }

}
