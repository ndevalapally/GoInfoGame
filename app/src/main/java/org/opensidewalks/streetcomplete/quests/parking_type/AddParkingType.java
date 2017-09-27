package org.opensidewalks.streetcomplete.quests.parking_type;

import android.os.Bundle;

import org.opensidewalks.streetcomplete.data.osm.download.OverpassMapDataDao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import de.westnordost.streetcomplete.R;
import org.opensidewalks.streetcomplete.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.streetcomplete.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.streetcomplete.quests.AbstractQuestAnswerFragment;

public class AddParkingType extends SimpleOverpassQuestType
{
	@Inject public AddParkingType(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override
	protected String getTagFilters()
	{
		return "nodes, ways with amenity=parking and !parking";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new AddParkingTypeForm();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		List<String> values = answer.getStringArrayList(AddParkingTypeForm.OSM_VALUES);
		if(values != null  && values.size() == 1)
		{
			changes.add("parking", values.get(0));
		}
	}

	@Override public String getCommitMessage() { return "Add parking type"; }
	@Override public int getIcon() { return R.drawable.ic_quest_parking; }
	@Override public int getTitle(Map<String, String> tags)
	{
		return R.string.quest_parkingType_title;
	}
}