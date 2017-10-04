package org.opensidewalks.goinfo.quests.crossing_type;

import org.opensidewalks.goinfo.R;
import org.opensidewalks.goinfo.quests.ImageListQuestAnswerFragment;

public class AddCrossingTypeForm extends ImageListQuestAnswerFragment
{
    private final OsmItem[] TYPES = new OsmItem[] {
            new OsmItem("traffic_signals", R.drawable.crossing_type_signals, R.string.quest_crossing_type_signals),
            new OsmItem("uncontrolled", R.drawable.crossing_type_zebra, R.string.quest_crossing_type_uncontrolled),
            new OsmItem("unmarked", R.drawable.crossing_type_unmarked, R.string.quest_crossing_type_unmarked)
    };

    @Override protected OsmItem[] getItems() { return TYPES; }
    @Override protected int getItemsPerRow() { return 3; }
}
