package org.opensidewalks.goinfo.quests.tactile_paving;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.opensidewalks.goinfo.R;
import org.opensidewalks.goinfo.quests.YesNoQuestAnswerFragment;

public class TactilePavingBusStopForm extends YesNoQuestAnswerFragment
{
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = super.onCreateView(inflater, container, savedInstanceState);
		setContentView(R.layout.quest_tactile_paving);
		return view;
	}
}
