package org.opensidewalks.goinfo.quests.tactile_paving;

import android.os.Bundle;

import java.util.Map;

import javax.inject.Inject;

import de.westnordost.goinfo.R;
import org.opensidewalks.goinfo.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.goinfo.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.goinfo.data.osm.download.OverpassMapDataDao;
import org.opensidewalks.goinfo.quests.AbstractQuestAnswerFragment;
import org.opensidewalks.goinfo.quests.YesNoQuestAnswerFragment;

public class AddTactilePavingBusStop extends SimpleOverpassQuestType
{
	@Inject public AddTactilePavingBusStop(OverpassMapDataDao overpassServer)
	{
		super(overpassServer);
	}

	@Override protected String getTagFilters()
	{
		return "nodes with (public_transport=platform or (highway=bus_stop and public_transport!=stop_position)) and !tactile_paving";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new TactilePavingBusStopForm();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		String yesno = answer.getBoolean(YesNoQuestAnswerFragment.ANSWER) ? "yes" : "no";
		changes.add("tactile_paving", yesno);
	}

	@Override public String getCommitMessage() { return "Add tactile pavings on bus stops"; }
	@Override public int getIcon() { return R.drawable.ic_quest_blind_bus; }
	@Override public int getTitle(Map<String,String> tags)
	{
		boolean hasName = tags.containsKey("name");
		if(hasName) return R.string.quest_tactilePaving_title_name_bus;
		else        return R.string.quest_tactilePaving_title_bus;
	}
}
