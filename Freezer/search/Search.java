package com.example.Freezer.search;

import com.example.Freezer.Food;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Search
{
    private static final String NAME_TAG = "name";
    private static final String TYPE_TAG = "type";
    private static final String DATE_ADDED_TAG = "date added";

    private String searchBy;
    private String searchString;

    public Search()
    {

    }

    public Search(String searchBy, String searchString)
    {
        this.setSearchBy(searchBy);
        this.setSearchString(searchString);
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<Food> getMatchingItems(List<Food> items)
    {
        List<Food> matchingItems = new ArrayList<>();
        for (Food item : items)
        {
            boolean matches = false;
            if (searchBy.equalsIgnoreCase(NAME_TAG))
            {
                matches = item.getName().toLowerCase().contains(searchString.toLowerCase());
            }
            else if (searchBy.equalsIgnoreCase(TYPE_TAG))
            {
                matches = item.getType().toLowerCase().contains(searchString.toLowerCase());
            }
            else if (searchBy.equalsIgnoreCase(DATE_ADDED_TAG))
            {
                matches = DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(item.getDate()))
                        .equals(searchString);
            }
            if (matches)
            {
                matchingItems.add(item);
            }
        }

        return matchingItems;
    }
}
