package ledge.muscleup.presentation;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ledge.muscleup.R;

/**
 * This class contains a method for populating the list
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-07
 */

public class ListManager {

    /**
     *  Used to insert a List into a list pane defined in the xml as "list_panel"
     * @param arrayList
     */
    public void populateList(Activity act, List arrayList) {
        ArrayAdapter adapter = new ArrayAdapter<>(act,
                R.layout.activity_listview, arrayList);

        ListView listView = (ListView) act.findViewById(R.id.list_panel);
        listView.setAdapter(adapter);
    }

}