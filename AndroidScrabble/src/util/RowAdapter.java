package util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RowAdapter extends BaseAdapter{
    private List<Row> elements;
    private Context context;

    public RowAdapter(Context c, List<Row> rows) {
        this.elements = rows;
        this.context = c;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return elements.get(position);
    }
    
    public void clear(){
    	elements.clear();
    }
}