package work;

import java.beans.Transient;
import java.io.Serializable;


public class DictItem implements Serializable, Cloneable {
	
	public DictItem(String idSlow, String code, String codeMnem, String name,
			String altName, String shortName, String description, String daneDod1, String daneDod2) {
		super();
		this.idSlow = idSlow;
		this.code = code;
		this.codeMnem = codeMnem;
		this.name = name;
		this.altName = altName;
		this.shortName = shortName;
		this.description = description;
		this.daneDod1 = daneDod1;
		this.daneDod2 = daneDod2;
	}

	protected String idSlow;
	
	protected String wersja;

	protected String code;
    protected String codeMnem;
    protected String name;
  
    /**
     * zpalka
     * Dodanie (tymczasowo) kombinowanego id - id slownika + code, przy uzywaniu tylko code jako id nie sa rozrozniane
     * slowniki (przez cache).
     */
    protected String codeAndDict;
    
//    @Transient
    protected String shortName;
    protected String altName;
//
    protected String description;
    
    protected boolean invalid;
    
      protected String daneDod1;
    protected String daneDod2;
    protected String grupa;

    public DictItem() {
    	this.invalid = false;
    }

    
    public DictItem(String code, String name) {
    	super();
    	this.code = code;
    	this.name = name;
    }

    public DictItem(String code, String name, String wersja, String idSlow) {
    	super();
    	this.code = code;
    	this.name = name;
    	this.wersja = wersja;
       	this.idSlow = idSlow;
     }
    
    public DictItem(String code, String name, String daneDod1) {
    	super();
    	this.code = code;
    	this.name = name;
    	this.daneDod1 = daneDod1;
     }
    
    public static void copy(DictItem newDictItem, DictItem dictItem) {
        if (newDictItem == null || dictItem == null) {
            return;
        }
        newDictItem.idSlow = dictItem.idSlow;
        newDictItem.wersja = dictItem.wersja;
        newDictItem.code = dictItem.code;
        newDictItem.codeMnem = dictItem.codeMnem;
        newDictItem.name = dictItem.name;
        newDictItem.shortName = dictItem.shortName;
        newDictItem.altName = dictItem.altName;
        newDictItem.description = dictItem.description;
        newDictItem.invalid = dictItem.invalid;
        newDictItem.daneDod1 = dictItem.daneDod1;
        newDictItem.daneDod2 = dictItem.daneDod2;
        newDictItem.grupa = dictItem.grupa;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeMnem() {
        return codeMnem;
    }

    public void setCodeMnem(String codemnem) {
        this.codeMnem = codemnem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public String getDaneDod1() {
        return daneDod1;
    }

    public void setDaneDod1(String daneDod1) {
        this.daneDod1 = daneDod1;
    }

    public String getDaneDod2() {
        return daneDod2;
    }

    public void setDaneDod2(String daneDod2) {
        this.daneDod2 = daneDod2;
    }
    
	public String getWersja() {
		return wersja;
	}


	public void setWersja(String wersja) {
		this.wersja = wersja;
	}

	public String getIdSlow() {
		return idSlow;
	}


	public void setIdSlow(String idSlow) {
		this.idSlow = idSlow;
	}

    public String getCodeAndDict() {
        return codeAndDict;
    }

    public void setCodeAndDict(String codeAndDict) {
        this.codeAndDict = codeAndDict;
    }

    public String getGrupa()
    {
        return grupa;
    }

    public void setGrupa(String grupa)
    {
        this.grupa = grupa;
    }

    public DictItemData getDiData() {
        return diData;
    }

    public void setDiData(DictItemData diData) {
        this.diData = diData;
    }
}

 