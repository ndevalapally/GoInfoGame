package org.opensidewalks.goinfo.quests.way_lit;

import android.os.Bundle;
import android.text.TextUtils;

import org.opensidewalks.goinfo.data.osm.download.OverpassMapDataDao;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import de.westnordost.goinfo.R;
import org.opensidewalks.goinfo.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.goinfo.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.goinfo.quests.AbstractQuestAnswerFragment;
import org.opensidewalks.goinfo.quests.YesNoQuestAnswerFragment;

public class AddWayLit extends SimpleOverpassQuestType
{
	private static final String[] LIT_RESIDENTIAL_ROADS = { "residential", "living_street", "pedestrian" };

	private static final String[] LIT_NON_RESIDENTIAL_ROADS = {
			"primary", "secondary", "tertiary", "unclassified", "service",
	};

	private static final String[] LIT_WAYS = { "footway", "cycleway" };

	@Inject public AddWayLit(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override protected String getTagFilters()
	{
		/* Using sidewalk as a tell-tale tag for (urban) streets which reached a certain level of
		   development. I.e. non-urban streets will usually not even be lit in industrialized
		   countries.
		   Also, only include paths only for those which are equal to footway/cycleway to exclude
		   most hike paths and trails.

		   See #427 for discussion. */

		return "ways with " +
				"(" +
				" highway ~ " + TextUtils.join("|", LIT_RESIDENTIAL_ROADS) +
				" or" +
				" highway ~ " + TextUtils.join("|", LIT_NON_RESIDENTIAL_ROADS) +
				" and ( sidewalk ~ both|left|right|yes|separate or source:maxspeed ~ .+:urban )" +
				" or" +
				" highway ~ " + TextUtils.join("|", LIT_WAYS) +
				" or" +
				" highway = path and (foot = designated or bicycle = designated)" +
				")" +
				" and !lit and (access !~ private|no or (foot and foot !~ private|no))";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new WayLitForm();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		String other = answer.getString(WayLitForm.OTHER_ANSWER);
		if (other != null)
		{
			changes.add("lit", other);
		}
		else
		{
			changes.add("lit", answer.getBoolean(YesNoQuestAnswerFragment.ANSWER) ? "yes" : "no");
		}
	}

	@Override public String getCommitMessage() { return "Add way lit"; }
	@Override public int getIcon() { return R.drawable.ic_quest_lantern; }
	@Override public int getTitle(Map<String,String> tags)
	{
		String type = tags.get("highway");
		boolean hasName = tags.containsKey("name");
		boolean isRoad = Arrays.asList(LIT_NON_RESIDENTIAL_ROADS).contains(type) ||
				Arrays.asList(LIT_RESIDENTIAL_ROADS).contains(type);
		if (isRoad)
		{
			if (hasName) return R.string.quest_way_lit_named_road_title;
			else         return R.string.quest_way_lit_road_title;
		}
		else
		{
			if (hasName) return R.string.quest_way_lit_named_title;
			else         return R.string.quest_way_lit_title;
		}
	}
}
