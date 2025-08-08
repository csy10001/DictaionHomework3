package hello.jpatest.snack.controller;

import hello.jpatest.snack.dto.SnackRequest;
import hello.jpatest.snack.dto.SnackResponse;
import hello.jpatest.snack.service.SnackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SnackController {

    private final SnackService snackService;

    @PostMapping("/snacks")
    public ResponseEntity<SnackResponse> saveSnack(
            @RequestBody SnackRequest request
    ) {
        return ResponseEntity.ok(snackService.save(request));
    }

    @GetMapping("/snacks")
    public ResponseEntity<Iterable<SnackResponse>> getAllSnacks() {
        return ResponseEntity.ok(snackService.findAll());
    }

    @GetMapping("/snacks/{id}") // 컨트롤러에 스낵 단건 조회 기능 !!
    public ResponseEntity<SnackResponse> getSnack(
            @PathVariable Long id
            ) {
            return ResponseEntity.ok(snackService.findById(id));
    }

    @PutMapping("/snacks/{id}") // 컨트롤러에 스낵 수정 기능 !!
    public ResponseEntity<SnackResponse> updateSnack(
            @PathVariable Long id,
            @RequestBody SnackRequest request) {
        return ResponseEntity.ok(snackService.update(id, request));
    }
}
