package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by augusto on 9/18/16.
 */
public class CloudService {

    private Integer id;
    private List<VirtualMachine> virtualMachineList;

    public CloudService(Integer id){
        this.id = id;
        this.virtualMachineList = new ArrayList<>();
    }

    public List<VirtualMachine> getVirtualMachineList() {
        return virtualMachineList;
    }

    public void setVirtualMachineList(List<VirtualMachine> virtualMachineList) {
        this.virtualMachineList = virtualMachineList;
    }

    public void addVirtualMachine(VirtualMachine vm) {
        virtualMachineList.add(vm);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
