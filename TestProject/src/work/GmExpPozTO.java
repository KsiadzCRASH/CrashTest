/**
 * 
 */
package work;

import java.util.ArrayList;
import java.util.List;

public class GmExpPozTO  {
	
	private static final long serialVersionUID = 1L;

	private GmExpPoz gmExpPoz;

	private String expTypDokMM;
	private String expRodzaj;
	private String expWartosc;
	
	private String expStronaKonta;
	private String expStronaKartoteka;
	private String expStronaKosztSzczegolowy;
	
	private List<DictItem> wartVatList;
	private List<DictItem> wartExpList;
	
	private List<DictItem> symbolKontaList;
	private List<DictItem> opisList;
	private List<DictItem> kartotekaList;
	private List<DictItem> kosztSzczegolowyList;
	
	private List<DictItem> magList;
	private List<DictItem> dokRodzList;
	
	public Boolean zmienZnak()
	{
		return gmExpPoz != null && gmExpPoz.getZmienZnak().getValue() == 0;
	}
	
	public String getExpTypDokMM() {
		return expTypDokMM;
	}
	public void setExpTypDokMM(String expTypDokMM) {
		this.expTypDokMM = expTypDokMM;
	}
	public GmExpPoz getGmExpPoz() {
		return gmExpPoz;
	}
	public void setGmExpPoz(GmExpPoz gmExpPoz) {
		this.gmExpPoz = gmExpPoz;
	}
	public String getExpWartosc() {
		return expWartosc;
	}
	public void setExpWartosc(String expWartosc) {
		this.expWartosc = expWartosc;
	}
	public String getExpStronaKonta() {
		return expStronaKonta;
	}
	public void setExpStronaKonta(String stronaKonta) {
		this.expStronaKonta = stronaKonta;
	}
	public List<DictItem> getWartVatList() {
		return wartVatList;
	}
	public void setWartVatList(List<DictItem> wartVatList) {
		this.wartVatList = wartVatList;
	}
	public List<DictItem> getWartExpList() {
		return wartExpList;
	}
	public void setWartExpList(List<DictItem> wartExpList) {
		this.wartExpList = wartExpList;
	}
	public List<DictItem> getSymbolKontaList() {
		return this.symbolKontaList;
	}
	public void setSymbolKontaList(List<DictItem> symbolKontaList) {
		this.symbolKontaList = symbolKontaList;
	}
	public List<DictItem> getOpisList() {
		return opisList;
	}
	public void setOpisList(List<DictItem> opisList) {
		this.opisList = opisList;
	}
	public List<DictItem> getMagList() {
		return magList;
	}
	public void setMagList(List<DictItem> magList) {
		this.magList = magList;
	}
	public List<DictItem> getDokRodzList() {
		return dokRodzList;
	}
	public void setDokRodzList(List<DictItem> dokRodzList) {
		this.dokRodzList = dokRodzList;
	}
	public String getExpRodzaj() {
		return expRodzaj;
	}
	public void setExpRodzaj(String expRodzaj) {
		this.expRodzaj = expRodzaj;
	}
	public String getExpStronaKartoteka() {
		return expStronaKartoteka;
	}
	public void setExpStronaKartoteka(String expStronaKartoteka) {
		this.expStronaKartoteka = expStronaKartoteka;
	}
	public String getExpStronaKosztSzczegolowy() {
		return expStronaKosztSzczegolowy;
	}
	public void setExpStronaKosztSzczegolowy(String expStronaKosztSzczegolowy) {
		this.expStronaKosztSzczegolowy = expStronaKosztSzczegolowy;
	}
	public List<DictItem> getKartotekaList() {
		return kartotekaList;
	}
	public void setKartotekaList(List<DictItem> kartotekaList) {
		this.kartotekaList = kartotekaList;
	}
	public List<DictItem> getKosztSzczegolowyList() {
		return kosztSzczegolowyList;
	}
	public void setKosztSzczegolowyList(List<DictItem> kosztSzczegolowyList) {
		this.kosztSzczegolowyList = kosztSzczegolowyList;
	}

	public static class SelectGenerator
	{
		private class Query
		{
			private String select = "";
			private String into = "";
			private String from = "";
			private String where= "";
			private String groupBy = "";
			private String having = "";
			private String orderBy = "";
			
			private boolean kontoKontr = false;
			private boolean kontoOpk = false;
			private String kontoOkpSymbol = null;
			
			private String compile()
			{
				String sSelect ="SELECT nag.dn_id as numTemp ";
			 	String sInto = "\nINTO :strKonto_Exp, :strKonto_Szcz, :strKarto_Exp, :strSymbol_Kontr, :srtSymbol_Opk, :numTemp, :strWartosc_Exp ";
				String sFrom = "\nFROM gmsl_mag m, gmsl_miejscskl ms, gmdoknag nag, gmdokpoz poz, gmsl_dokrodz dr ";
				String sWhere= "\nWHERE poz.dn_id = nag.dn_id AND poz.dp_fl_ksg = 'Z' AND dr.id_drodz = nag.id_drodz AND nag.dn_fl_ksg = 'Z' AND poz.id_ms = ms.id_ms AND ms.id_mag = m.id_mag ";
				String sGroupBy = "\nGROUP BY ";
				String sHaving = "\nHAVING ";
				String sOrderBy = "\nORDER BY ";
				
				if(kontoKontr)
				{
					select += ", k.konto_kontr as strSymbol_Kontr";
				}
				else 
				{
					select += ", null as strSymbol_Kontr";
				}
				
				if(kontoOpk)
				{
					select += ", " + kontoOkpSymbol + " as strSymbol_Opk";
				}
				else 
				{
					select += ", null as strSymbol_Opk";
				}
			
				
				if(select.length() > 0) 
				{
					select = select.substring(2);
				}
				select = sSelect + select;
				into = sInto;
				where = sWhere + where;
				from = sFrom + from;
				if(groupBy.length() > 0)
				{ 
					groupBy = groupBy.substring(1);
					groupBy = sGroupBy+groupBy;
					
				}
				if(having.length() > 0) 
				{
					having = sHaving + having;
				}
				
				if(orderBy.length() > 0) 
				{
					orderBy = orderBy.substring(1);
					orderBy = sOrderBy + orderBy;
				}
				
				return select+into+from+where+groupBy+having+orderBy;
			}
		}
		public String genrate(GmExpPozTO data)
		{
			Query tmpQuery = new Query();
			
			// Symbol konta
			genFromDiList(tmpQuery, data.getSymbolKontaList());
			tmpQuery.select += "as strKonto_Exp";
			
			//Konto szczegolowe
			
			// Kartoteka
	
			
			return tmpQuery.compile();
		}
		
		private void genFromDiList(Query tmpQuery, List<DictItem> data)
		{
			for (int  i= 0; i < data.size(); i++)
			{
				DictItem dictItem = data.get(i);
				
				genFromDictItem(tmpQuery, dictItem);
			}
			
		}
		private void genFromDictItem(Query tmpQuery, DictItem di)
		{
			String c = di.getCode();
			
			// Grupa analityczna
			if(c.startsWith("A"))
			{
				String number = c.substring(1);
				c = c.toLowerCase();
				
				tmpQuery.select +="|| "+ c +".anali_konto";
				tmpQuery.from += ", gmsl_granali"+number+" "+c+" ";
				tmpQuery.where  += " AND nag.anali"+number+"="+c+".anali_kod and nag.adali"+number+" is not null";
				tmpQuery.groupBy +=", "+ c+".anali_konto";
				tmpQuery.orderBy +=", " + c+".anali_konto";				
			}
			else if(c.equals("M"))
			{
				tmpQuery.select +="|| m.konto_fk";
				tmpQuery.groupBy +=", m.konto_fk";
				tmpQuery.orderBy +=", m.konto_fk";
			}
			else if(c.equals("K"))
			{
				tmpQuery.select +="|| k.konto_kontr";
				tmpQuery.from += ", gmsl_kontrah k";
				tmpQuery.where  += " AND k.id_kontr = nag.id_kontr";
				tmpQuery.groupBy +=", k.konto_kontr";
				tmpQuery.orderBy +=", k.konto_kontr";
			
				tmpQuery.kontoKontr = true;
			}
			else if(c.equals("O"))
			{
				tmpQuery.select +="|| o.odb_symbo";
				tmpQuery.from += ", gmodbiorcy o";
				tmpQuery.where  += " AND k.odb_id = nag.odb_id";//?
				tmpQuery.groupBy +=", o.odb_symbo";
				tmpQuery.orderBy +=", o.odb_symbo";
				
				tmpQuery.kontoOkpSymbol =  "o.odb_symbo";
				tmpQuery.kontoOpk = true;
				
			}
			else if(c.equals("G"))
			{
				tmpQuery.select +="|| rk.rodza_konto";
				tmpQuery.from += ", gmsl_korza_koszt rk, gmsl_tow tow, gmsl_towlok tlok, gmdostawy dost";
				tmpQuery.where  += " AND rk.rodza_kod = tow.rodza_kod AND tow.id_tow = tlok.id_tow " +
									 "AND tlok.id_towlok = dost.id_towlok AND dost.id_dostawy = poz.id_dostawy ";
				tmpQuery.groupBy +=", rk.rodza_konto";
				tmpQuery.orderBy +=", rk.rodza_konto";
				
			}
			else if(c.equals("R"))
			{
				tmpQuery.select +="|| rel.konto_rel";
				tmpQuery.from += ", gmrelewy rel";
				tmpQuery.where  += " poz.id_rel = rel.id_rel";
				tmpQuery.groupBy +=", rel.konto_rel";
				tmpQuery.orderBy +=", rel.konto_rel";
			}		
			else if(c.equals("P"))
			{
				tmpQuery.select +="|| opk.opk_symbo";
				tmpQuery.from += ", gmodbiorcy o, gmodbiorcy_opk op, opk_katal opk, gmokres ok";
				tmpQuery.where  += " o.odb_id = nag.odb_id  AND o.odb_id = op.odb_id AND op.opk_id = opk.opk_id AND op.rok = opk.rok AND op.bid = opk.bid AND nag.id_okr = ok.id_okr AND substr(ok.miesrok,3,4) = op.rok AND op.bid = :g_GM_nBid";
				tmpQuery.groupBy +=", opk.opk_symbo";
				tmpQuery.orderBy +=", opk.opk_symbo";

			}
			else if(c.equals("#"))
			{
				tmpQuery.select +="|| " +'\'' + di.getDaneDod1() + '\'';
				tmpQuery.groupBy +=", " +'\'' + di.getDaneDod1() + '\'';
				tmpQuery.orderBy +=", " +'\'' + di.getDaneDod1() + '\'';
			}
		}
		
		
	}
	public static class SchemaParser 
	{
		public String convertDataToSchema(GmExpPozTO data)
		{
			String schema = "@";
			
			// Budowa konta : Segment 1
			if(data.getSymbolKontaList() != null && data.getSymbolKontaList().size() > 0)
			{	
				schema += dictListToStr(data.getSymbolKontaList(), "\u007e", true);
				if(data.getExpStronaKonta() != null) schema += '\u007e'+data.getExpStronaKonta();
			}
			schema += "@";
			
			// Wartosci eksportu : Segment 2
			schema += data.getExpWartosc() + ';';
			if(data.getWartVatList() != null && data.getWartVatList().size() > 0)
			{
				for (DictItem diItem : data.getWartVatList()) {
					String code = diItem.getCode();
					
					if(code.charAt(0) == '0')
					{
						code = code.substring(1);
					}
					
					diItem.setCode(code);
				}
				
				schema += dictListToStr(data.getWartVatList(), ";", false);
			}
			schema += data.getExpRodzaj() + ';';
			schema += "@";
			
			// Mag i DokRodz : Segment 3
			if(data.getMagList() != null && data.getMagList().size() > 0)
			{
				schema += dictListToStr(data.getMagList(), ";", false);
			}
			
			schema += '^';
			
			if(data.getDokRodzList() != null && data.getDokRodzList().size() > 0)
			{
				schema += dictListToStr(data.getDokRodzList(), ";", false);
			}
			else 
			{
				schema += "0;";
			}
			schema += "@";
			
			// Koszt Szczegolowy : Segment 4
			if(data.getKosztSzczegolowyList() != null && data.getKosztSzczegolowyList().size() > 0)
			{	
				schema += dictListToStr(data.getKosztSzczegolowyList(), "\u007e", true);
				if(data.getExpStronaKosztSzczegolowy() != null) schema += '\u007e'+data.getExpStronaKosztSzczegolowy();
			}
			schema += "@";
			
			// Opis : Segment 5
			if(data.getOpisList() != null && data.getOpisList().size() > 0)
			{
				schema += dictListToStr(data.getOpisList(), "\u007e", true);
			}
			schema += "@";
			
			// TypMM : Segment 6
			if(data.getExpTypDokMM() != null)
			{
				schema += data.getExpTypDokMM();
			}
			schema += "@";
			
			// Kartoteka : Segment 7
			if(data.getKartotekaList() != null && data.getKartotekaList().size() > 0)
			{	
				schema += dictListToStr(data.getKartotekaList(), "\u007e", true);
				if(data.getExpStronaKartoteka() != null) schema += '\u007e'+data.getExpStronaKartoteka();
			}
			schema += "@";
			
			return schema;
		} 
		
		public void convertSchemaToData(GmExpPozTO data, String schema)
		{
			String[] schemaParts = schema.split("@");
			
			if(schemaParts.length >= 2)
			{
				parseKonto(schemaParts[1], data); 
			}	
			if(schemaParts.length >= 3)
			{
				parseExpWart(schemaParts[2], data);
			}
			if(schemaParts.length >= 4)
			{
				parseMagAndDokRodz(schemaParts[3], data);
			}
			if(schemaParts.length >= 5)
			{
				parseKosztSzczegolowy(schemaParts[4], data);
			}
			if(schemaParts.length >= 6)
			{
				parseOpis(schemaParts[5], data);
			}
			if(schemaParts.length >= 7)
			{
				parseTypMM(schemaParts[6], data);
			}
			if(schemaParts.length >= 8)
			{
				parseKartoteka(schemaParts[7], data);
			}
			
		} 
		// GMEXPPOZTO TO SCHEMA
		private String dictListToStr(List<DictItem> diList, String sep, Boolean sepFront)
		{
			String result = "";
			String sepFrontStr = sepFront ? sep : "";
			String sepBackStr = !sepFront ? sep : "";;
			
			for (DictItem dictItem : diList) 
			{
				if(dictItem.getCode().equals("#"))
				{
					result += sepFrontStr + "#" + dictItem.getDaneDod1() + "#" + sepBackStr;
				}
				else 
				{
					result += sepFrontStr + dictItem.getCode() + sepBackStr;
				}
			}
			
			return result;
		}
		
		// SCHEMA TO GMEXPPOZTO
		
		private void parseKartoteka(String schema, GmExpPozTO target) 
		{
			String[] valueSplit = schema.split("\u007e");
			List<DictItem> kartotekaDiList = parseSchema(valueSplit);
			
			target.setExpStronaKartoteka(valueSplit[valueSplit.length-1]);
			target.setKartotekaList(kartotekaDiList);
		}

		private void parseKosztSzczegolowy(String schema, GmExpPozTO target)
		{
			String[] valueSplit = schema.split("\u007e");
			List<DictItem> symbolKosztSzczegolowyDiList = parseSchema(valueSplit);
			
			target.setExpStronaKosztSzczegolowy(valueSplit[valueSplit.length-1]);
			target.setKosztSzczegolowyList(symbolKosztSzczegolowyDiList);
		}
		private void parseOpis(String schema, GmExpPozTO target)
		{
			String[] valueSplit = schema.split("\u007e");
			List<DictItem> symbolOpisDiList = parseSchema(valueSplit, valueSplit.length);
			
			target.setOpisList(symbolOpisDiList);
		}
		private void parseKonto(String schema, GmExpPozTO target)
		{
			String[] valueSplit = schema.split("\u007e");
			List<DictItem> symbolKontaDiList = parseSchema(valueSplit);
			
			target.setExpStronaKonta(valueSplit[valueSplit.length-1]);
			target.setSymbolKontaList(symbolKontaDiList);
		}	
		private void parseTypMM(String schema, GmExpPozTO target) 
		{
			if(schema.length()  > 0 )
			{
				target.setExpTypDokMM(schema);
			}
			else
			{
				target.setExpTypDokMM(null);
			}
		}
		private void parseExpWart(String schema, GmExpPozTO target)
		{
			String expWart;
			String expRodzaj;
			
			String[] splitParts = schema.split(";");
			
			expWart = splitParts[0];
			expRodzaj = splitParts[splitParts.length - 1];
			
			if(splitParts.length > 2)
			{
				List<DictItem> wartVatList = new ArrayList<DictItem>();

				expWart = splitParts[0];
				
				for (int i = 1; i < splitParts.length - 1; i++) 
				{
					String part = splitParts[i];
					
					if(part.length() == 1) part = '0'+ part;
					
					wartVatList.add(new DictItem(part,null));
				}
			
				target.setWartVatList(wartVatList);
			}
			
			target.setExpRodzaj(expRodzaj);
			target.setExpWartosc(expWart);
		}
		private void parseMagAndDokRodz(String schema, GmExpPozTO target)
		{
			List<DictItem> diList;
			
			String[] valueSplit = schema.split("\\u005e");
			
			diList = pareseLictList(valueSplit[0]);
			target.setMagList(diList);
			
			diList = pareseLictList(valueSplit[1]);
			target.setDokRodzList(diList);
		}
		
		private List<DictItem> pareseLictList(String schema)
		{
			List<DictItem> diList = new ArrayList<DictItem>();
			String[] valueSplit = schema.split(";");
			
			for (String part : valueSplit)
			{
				diList.add( new DictItem(part, null));
			}
			
			return diList;
		}
		// Helper
		private List<DictItem> parseSchema(String[] valueSplit)
		{
			return parseSchema(valueSplit, valueSplit.length -1);
		}
		private List<DictItem> parseSchema(String[] valueSplit, int length)
		{
			List<DictItem> budowaKontaDiList = new ArrayList<DictItem>();
			
			for (int i = 1; i < length; i++)
			{
				DictItem diPart;
				String part = valueSplit[i];
				
				if(part.contains("#"))
				{
					String value = part.substring(part.indexOf('#')+1, part.lastIndexOf('#'));
					diPart = new DictItem("#", null);
					diPart.setDaneDod1(value);
					budowaKontaDiList.add(diPart);
				}
				else 
				{
					diPart = new DictItem(part, null);
					budowaKontaDiList.add(diPart);
				}
				
			}
			return budowaKontaDiList;
		}
		
	}
	
	
}
