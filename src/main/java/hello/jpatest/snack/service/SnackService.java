package hello.jpatest.snack.service;

import hello.jpatest.snack.dto.SnackRequest;
import hello.jpatest.snack.dto.SnackResponse;
import hello.jpatest.snack.entity.Snack;
import hello.jpatest.snack.repository.SnackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnackService {

    private final SnackRepository snackRepository;

    @Transactional
    public SnackResponse save(SnackRequest request) {
        Snack snack = new Snack(request.getName());
        Snack savedSnack = snackRepository.save(snack);
        return new SnackResponse(
                savedSnack.getId(),
                savedSnack.getName(),
                savedSnack.getPrice()
        );
    }

    @Transactional(readOnly = true)
    public List<SnackResponse> findAll() {
        List<Snack> snacks = snackRepository.findAll();
        List<SnackResponse> list = new ArrayList<>();
        for (Snack snack : snacks) {
            SnackResponse snackResponse = new SnackResponse(
                    snack.getId(),
                    snack.getName(),
                    snack.getPrice()
            );
            list.add(snackResponse);
        }
        return list;
    }

    @Transactional
    public SnackResponse findById(Long snackId) {
        Snack snack = snackRepository.findById(snackId).orElseThrow(
                ()-> new IllegalArgumentException("해당 과자는 없습니다")
        );
        return new SnackResponse(
                snack.getId(),
                snack.getName(),
                snack.getPrice()
        );
    }

    @Transactional
    public SnackResponse update(Long snackId, SnackRequest request) {
        Snack snack = snackRepository.findById(snackId).orElseThrow(
                ()-> new IllegalArgumentException("해당 id의 과자가 없어요")
        );
        snack.setName(request.getName());
        snack.setPrice(request.getPrice());

        return new SnackResponse(
                snack.getId(),
                snack.getName(),
                snack.getPrice()
        );
    }
}
