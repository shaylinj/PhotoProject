package za.ac.nwu.domain.service;

import java.io.Serializable;
import java.util.Objects;

public class response<t> implements Serializable
{
    private final boolean successful;
    private final transient t data;

    public response(boolean successful, t payload){
        this.successful = successful;
        this.data = payload;
    }
    public boolean isSuccessful() {
        return successful;
    }
    public t getData()
    {
        return data;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        response<?> that = (response<?>) o;
        return successful == that.successful && Objects.equals(data, that.data);
    }
    @Override
    public int hashCode() {return Objects.hash(successful, data);}
    @Override
    public String toString() {
        return "GeneralResponse{" + "successful =" + successful + ", payload =" + data + '}';
    }
}
