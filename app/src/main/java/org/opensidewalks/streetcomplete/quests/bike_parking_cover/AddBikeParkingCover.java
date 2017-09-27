package org.opensidewalks.streetcomplete.quests.bike_parking_cover;

import android.os.Bundle;

import org.opensidewalks.streetcomplete.data.osm.download.OverpassMapDataDao;
import org.opensidewalks.streetcomplete.quests.AbstractQuestAnswerFragment;

import java.util.Map;

import javax.inject.Inject;

import de.westnordost.streetcomplete.R;
import org.opensidewalks.streetcomplete.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.streetcomplete.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.streetcomplete.quests.YesNoQuestAnswerFragment;

public class AddBikeParkingCover extends SimpleOverpassQuestType
{
	@Inject public AddBikeParkingCover(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override protected String getTagFilters()
	{
		return "nodes, ways with amenity=bicycle_parking and access!=private and !covered and bicycle_parking !~ shed|lockers|building";
	}

	public AbstractQuestAnswerFragment createForm() { return new YesNoQuestAnswerFragment(); }

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		String yesno = answer.getBoolean(YesNoQuestAnswerFragment.ANSWER) ? "yes" : "no";
		changes.add("covered", yesno);
	}

	@Override public String getCommitMessage() { return "Add bicycle parkings cover"; }
	@Override public int getIcon() { return R.drawable.ic_quest_bicycle_parking_cover; }
	@Override public int getTitle(Map<String,String> tags)
	{
		return R.string.quest_bicycleParkingCoveredStatus_title;
	}
}