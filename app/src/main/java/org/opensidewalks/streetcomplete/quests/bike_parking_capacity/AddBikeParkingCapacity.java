package org.opensidewalks.streetcomplete.quests.bike_parking_capacity;

import android.os.Bundle;

import org.opensidewalks.streetcomplete.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.streetcomplete.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.streetcomplete.data.osm.download.OverpassMapDataDao;
import org.opensidewalks.streetcomplete.quests.AbstractQuestAnswerFragment;

import java.util.Map;

import javax.inject.Inject;

import de.westnordost.streetcomplete.R;

public class AddBikeParkingCapacity extends SimpleOverpassQuestType
{
	@Inject public AddBikeParkingCapacity(OverpassMapDataDao overpassServer)
	{
		super(overpassServer);
	}

	@Override protected String getTagFilters()
	{
		return "nodes, ways with amenity=bicycle_parking and !capacity";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new AddBikeParkingCapacityForm();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		changes.add("capacity", ""+answer.getInt(AddBikeParkingCapacityForm.BIKE_PARKING_CAPACITY));
	}

	@Override public String getCommitMessage() { return "Add bicycle parking capacities"; }
	@Override public int getIcon() { return R.drawable.ic_quest_bicycle_parking; }
	@Override public int getTitle(Map<String, String> tags)
	{
		return R.string.quest_bikeParkingCapacity_title;
	}
}