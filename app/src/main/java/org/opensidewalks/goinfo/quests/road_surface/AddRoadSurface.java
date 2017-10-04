package org.opensidewalks.goinfo.quests.road_surface;

import android.os.Bundle;
import android.text.TextUtils;

import org.opensidewalks.goinfo.data.osm.download.OverpassMapDataDao;

import java.util.Map;

import javax.inject.Inject;

import org.opensidewalks.goinfo.R;
import org.opensidewalks.goinfo.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.goinfo.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.goinfo.quests.AbstractQuestAnswerFragment;

public class AddRoadSurface extends SimpleOverpassQuestType
{
	// well, all roads have surfaces, what I mean is that not all ways with highway key are
	// "something with a surface"
	private static final String[] ROADS_WITH_SURFACES = {
			// "trunk","trunk_link","motorway","motorway_link", // too much, motorways are almost by definition asphalt (or concrete)
			"primary", "primary_link", "secondary", "secondary_link", "tertiary", "tertiary_link",
			"unclassified", "residential", "living_street", "pedestrian",
			"track", "road",
			/*"service", */ // this is too much, and the information value is very low
	};

	@Inject public AddRoadSurface(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override protected String getTagFilters()
	{
		return " ways with highway ~ " + TextUtils.join("|",ROADS_WITH_SURFACES) + " and" +
			   " !surface and (access !~ private|no or (foot and foot !~ private|no))";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new AddRoadSurfaceForm();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		changes.add("surface", answer.getString(AddRoadSurfaceForm.SURFACE));
	}

	@Override public String getCommitMessage() { return "Add road surfaces"; }
	@Override public int getIcon() { return R.drawable.ic_quest_street_surface; }
	@Override public int getTitle(Map<String, String> tags)
	{
		boolean hasName = tags.containsKey("name");
		if(hasName) return R.string.quest_streetSurface_name_title;
		else        return R.string.quest_streetSurface_title;
	}
}
