package com.cnacex.comm.wtc;

import java.io.*;
import java.lang.*;
import java.util.*;
import weblogic.wtc.jatmi.*;

public final class CnacexFld
	implements weblogic.wtc.jatmi.FldTbl
{
	Hashtable nametofieldHashTable;
	Hashtable fieldtonameHashTable;
	/** number: 101  type: long */
	public final static int PHF_VER = 33554533;
	/** number: 102  type: string */
	public final static int PHF_REQSYS = 167772262;
	/** number: 103  type: string */
	public final static int PHF_REQNODE = 167772263;
	/** number: 104  type: string */
	public final static int PHF_TXCODE = 167772264;
	/** number: 105  type: long */
	public final static int PHF_REQNO = 33554537;
	/** number: 106  type: string */
	public final static int PHF_ENCODE = 167772266;
	/** number: 107  type: string */
	public final static int PHF_DYNAUTH = 167772267;
	/** number: 108  type: long */
	public final static int PHF_MAC = 33554540;
	/** number: 109  type: carray */
	public final static int PBF_DATA = 201326701;
	/** number: 110  type: string */
	public final static int PHF_RSPSYS = 167772270;
	/** number: 111  type: string */
	public final static int PHF_RSPNODE = 167772271;
	/** number: 112  type: long */
	public final static int PHF_RSPNO = 33554544;
	/** number: 113  type: long */
	public final static int PHF_RSPSTAT = 33554545;

	public String Fldid_to_name(int fldid)
	{
		if ( fieldtonameHashTable == null ) {
			fieldtonameHashTable = new Hashtable();
			fieldtonameHashTable.put(new Integer(PHF_VER), "PHF_VER");
			fieldtonameHashTable.put(new Integer(PHF_REQSYS), "PHF_REQSYS");
			fieldtonameHashTable.put(new Integer(PHF_REQNODE), "PHF_REQNODE");
			fieldtonameHashTable.put(new Integer(PHF_TXCODE), "PHF_TXCODE");
			fieldtonameHashTable.put(new Integer(PHF_REQNO), "PHF_REQNO");
			fieldtonameHashTable.put(new Integer(PHF_ENCODE), "PHF_ENCODE");
			fieldtonameHashTable.put(new Integer(PHF_DYNAUTH), "PHF_DYNAUTH");
			fieldtonameHashTable.put(new Integer(PHF_MAC), "PHF_MAC");
			fieldtonameHashTable.put(new Integer(PBF_DATA), "PBF_DATA");
			fieldtonameHashTable.put(new Integer(PHF_RSPSYS), "PHF_RSPSYS");
			fieldtonameHashTable.put(new Integer(PHF_RSPNODE), "PHF_RSPNODE");
			fieldtonameHashTable.put(new Integer(PHF_RSPNO), "PHF_RSPNO");
			fieldtonameHashTable.put(new Integer(PHF_RSPSTAT), "PHF_RSPSTAT");
		}

		return ((String)fieldtonameHashTable.get(new Integer(fldid)));
	}

	public int name_to_Fldid(String name)
	{
		if ( nametofieldHashTable == null ) {
			nametofieldHashTable = new Hashtable();
			nametofieldHashTable.put("PHF_VER", new Integer(PHF_VER));
			nametofieldHashTable.put("PHF_REQSYS", new Integer(PHF_REQSYS));
			nametofieldHashTable.put("PHF_REQNODE", new Integer(PHF_REQNODE));
			nametofieldHashTable.put("PHF_TXCODE", new Integer(PHF_TXCODE));
			nametofieldHashTable.put("PHF_REQNO", new Integer(PHF_REQNO));
			nametofieldHashTable.put("PHF_ENCODE", new Integer(PHF_ENCODE));
			nametofieldHashTable.put("PHF_DYNAUTH", new Integer(PHF_DYNAUTH));
			nametofieldHashTable.put("PHF_MAC", new Integer(PHF_MAC));
			nametofieldHashTable.put("PBF_DATA", new Integer(PBF_DATA));
			nametofieldHashTable.put("PHF_RSPSYS", new Integer(PHF_RSPSYS));
			nametofieldHashTable.put("PHF_RSPNODE", new Integer(PHF_RSPNODE));
			nametofieldHashTable.put("PHF_RSPNO", new Integer(PHF_RSPNO));
			nametofieldHashTable.put("PHF_RSPSTAT", new Integer(PHF_RSPSTAT));
		}

		Integer fld = (Integer)nametofieldHashTable.get(name);
		if (fld == null) {
			return (-1);
		} else {
			return (fld.intValue());
		}
	}

	public String[] getFldNames()
	{
		String retval[] = new String[13];
		retval[0] = new String("PHF_VER");
		retval[1] = new String("PHF_REQSYS");
		retval[2] = new String("PHF_REQNODE");
		retval[3] = new String("PHF_TXCODE");
		retval[4] = new String("PHF_REQNO");
		retval[5] = new String("PHF_ENCODE");
		retval[6] = new String("PHF_DYNAUTH");
		retval[7] = new String("PHF_MAC");
		retval[8] = new String("PBF_DATA");
		retval[9] = new String("PHF_RSPSYS");
		retval[10] = new String("PHF_RSPNODE");
		retval[11] = new String("PHF_RSPNO");
		retval[12] = new String("PHF_RSPSTAT");
		return retval;
	}
}
