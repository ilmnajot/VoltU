package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.PortRepository;
import uz.ilmnajot.voltu.service.PortService;
@Service
public class PortServiceImpl implements PortService {

    private final PortRepository portRepository;

    public PortServiceImpl(PortRepository portRepository) {
        this.portRepository = portRepository;
    }
}
