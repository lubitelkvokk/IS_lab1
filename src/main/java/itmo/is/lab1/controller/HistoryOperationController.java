package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.additional.HistoryOperationDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.additional.HistoryOperationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryOperationController {

    private final HistoryOperationService historyOperationService;

    public HistoryOperationController(HistoryOperationService historyOperationService) {
        this.historyOperationService = historyOperationService;
    }

    @GetMapping
    public ResponseEntity<Page<HistoryOperationDTO>> getImportHistory(
            Pageable pageable,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(historyOperationService.getHistoryOperations(pageable, user));

    }

}
