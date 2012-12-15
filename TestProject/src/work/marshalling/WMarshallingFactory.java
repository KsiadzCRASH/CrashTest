package work.marshalling;


public interface WMarshallingFactory {

    public MarshallingProvider getProvider(RodzajKomunikatu rodzajKomunikatu, String wersja);
    
    public void registerProvider(RodzajKomunikatu rodzajKomunikatu, String wersja, MarshallingProvider provider);
}

