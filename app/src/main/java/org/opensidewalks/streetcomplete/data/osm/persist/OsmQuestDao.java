package org.opensidewalks.streetcomplete.data.osm.persist;

import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;

import org.opensidewalks.streetcomplete.data.QuestTypes;
import org.opensidewalks.streetcomplete.util.Serializer;

public class OsmQuestDao extends AOsmQuestDao
{

	@Inject public OsmQuestDao(SQLiteOpenHelper dbHelper, Serializer serializer, QuestTypes questTypeList)
	{
		super(dbHelper, serializer, questTypeList);
	}

	@Override protected String getTableName() { return OsmQuestTable.NAME; }
	@Override protected String getMergedViewName() { return OsmQuestTable.NAME_MERGED_VIEW; }
}