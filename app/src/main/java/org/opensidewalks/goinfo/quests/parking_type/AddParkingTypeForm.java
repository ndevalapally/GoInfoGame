package org.opensidewalks.goinfo.quests.parking_type;

import org.opensidewalks.goinfo.R;
import org.opensidewalks.goinfo.quests.ImageListQuestAnswerFragment;

public class AddParkingTypeForm extends ImageListQuestAnswerFragment
{
	private final OsmItem[] TYPES = new OsmItem[] {
			new OsmItem("surface", R.drawable.parking_type_surface, R.string.quest_parkingType_surface),
			new OsmItem("underground", R.drawable.parking_type_underground, R.string.quest_parkingType_underground),
			new OsmItem("multi-storey", R.drawable.parking_type_multistorey, R.string.quest_parkingType_multiStorage)
	};

	@Override protected OsmItem[] getItems() { return TYPES; }
	@Override protected int getItemsPerRow() { return 3; }
}
