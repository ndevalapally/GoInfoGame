package org.opensidewalks.goinfo.quests.fire_hydrant;

import android.os.Bundle;

import org.opensidewalks.goinfo.data.osm.download.OverpassMapDataDao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.opensidewalks.goinfo.R;
import org.opensidewalks.goinfo.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.goinfo.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.goinfo.quests.AbstractQuestAnswerFragment;

public class AddFireHydrantType extends SimpleOverpassQuestType
{
	@Inject public AddFireHydrantType(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override protected String getTagFilters()
	{
		return "nodes with emergency=fire_hydrant and !fire_hydrant:type";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new AddFireHydrantTypeForm();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		List<String> values = answer.getStringArrayList(AddFireHydrantTypeForm.OSM_VALUES);
		if(values != null  && values.size() == 1)
		{
			changes.add("fire_hydrant:type", values.get(0));
		}
	}

	@Override public String getCommitMessage() { return "Add fire hydrant type"; }
	@Override public int getIcon() { return R.drawable.ic_quest_fire_hydrant; }
	@Override public int getTitle(Map<String,String> tags)
	{
		return R.string.quest_fireHydrant_type_title;
	}
}
