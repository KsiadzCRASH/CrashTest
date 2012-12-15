package work.marshalling;

import java.util.HashMap;
import java.util.Map;

import pl.asseco.amms.model.common.marshalling.RodzajKomunikatu;


public class WMarshallingFactoryImpl implements WMarshallingFactory {

    
    private Map<MarshallingServiceKey, MarshallingProvider> marshallingProviders = 
        new HashMap<MarshallingServiceKey, MarshallingProvider>();
    
    
    public final MarshallingProvider getProvider(RodzajKomunikatu rodzajKomunikatu, String wersja) {
        
        return marshallingProviders.get(new MarshallingServiceKey(rodzajKomunikatu, wersja));
    }
    
    public final void registerProvider(RodzajKomunikatu rodzajKomunikatu, String wersja, 
         MarshallingProvider provider) {

         MarshallingServiceKey key = new MarshallingServiceKey(rodzajKomunikatu, wersja);
         if( marshallingProviders.containsKey(key)) {
             throw new IllegalArgumentException("Dla rodzaju: " + rodzajKomunikatu + " oraz wersji: "
                 + wersja + " istnieje zarejestrowany provider do marshallingu.");
         }
         marshallingProviders.put(key, provider);
    }
    
    private class MarshallingServiceKey {

        private RodzajKomunikatu rodzajKomunikatu;
        private String wersja;
        
        private MarshallingServiceKey() {
            super();
        }

        private MarshallingServiceKey(RodzajKomunikatu rodzajKomunikatu,
                String wersja) {
            super();
            this.rodzajKomunikatu = rodzajKomunikatu;
            this.wersja = wersja;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime
                    * result
                    + ((rodzajKomunikatu == null) ? 0 : rodzajKomunikatu
                            .hashCode());
            result = prime * result
                    + ((wersja == null) ? 0 : wersja.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MarshallingServiceKey other = (MarshallingServiceKey) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (rodzajKomunikatu == null) {
                if (other.rodzajKomunikatu != null)
                    return false;
            } else if (!rodzajKomunikatu.equals(other.rodzajKomunikatu))
                return false;
            if (wersja == null) {
                if (other.wersja != null)
                    return false;
            } else if (!wersja.equals(other.wersja))
                return false;
            return true;
        }

        private WMarshallingFactoryImpl getOuterType() {
            return WMarshallingFactoryImpl.this;
        }
        
    }
}

