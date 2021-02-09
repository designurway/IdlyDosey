
package com.designurway.idlidosa.a.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated("org.parceler.ParcelAnnotationProcessor")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class CustomerAddress$$Parcelable
    implements Parcelable, ParcelWrapper<com.designurway.idlidosa.a.model.CustomerAddress>
{

    private com.designurway.idlidosa.a.model.CustomerAddress customerAddress$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<CustomerAddress$$Parcelable>CREATOR = new Creator<CustomerAddress$$Parcelable>() {


        @Override
        public CustomerAddress$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new CustomerAddress$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public CustomerAddress$$Parcelable[] newArray(int size) {
            return new CustomerAddress$$Parcelable[size] ;
        }

    }
    ;

    public CustomerAddress$$Parcelable(com.designurway.idlidosa.a.model.CustomerAddress customerAddress$$2) {
        customerAddress$$0 = customerAddress$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(customerAddress$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.designurway.idlidosa.a.model.CustomerAddress customerAddress$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(customerAddress$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(customerAddress$$1));
            parcel$$1 .writeString(customerAddress$$1 .amount);
            parcel$$1 .writeString(customerAddress$$1 .name);
            parcel$$1 .writeString(customerAddress$$1 .cityAddress);
            parcel$$1 .writeString(customerAddress$$1 .mobile);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.designurway.idlidosa.a.model.CustomerAddress getParcel() {
        return customerAddress$$0;
    }

    public static com.designurway.idlidosa.a.model.CustomerAddress read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.designurway.idlidosa.a.model.CustomerAddress customerAddress$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            customerAddress$$4 = new com.designurway.idlidosa.a.model.CustomerAddress();
            identityMap$$1 .put(reservation$$0, customerAddress$$4);
            customerAddress$$4 .amount = parcel$$3 .readString();
            customerAddress$$4 .name = parcel$$3 .readString();
            customerAddress$$4 .cityAddress = parcel$$3 .readString();
            customerAddress$$4 .mobile = parcel$$3 .readString();
            com.designurway.idlidosa.a.model.CustomerAddress customerAddress$$3 = customerAddress$$4;
            identityMap$$1 .put(identity$$1, customerAddress$$3);
            return customerAddress$$3;
        }
    }

}
