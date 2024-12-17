package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.additional.HistoryOperationDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.additional.HistoryOperationService;
import itmo.is.lab1.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/import")
@Validated
public class ImportFileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private HistoryOperationService historyOperationService;

    @PostMapping
    public ResponseEntity<String> submit(@RequestParam("file") MultipartFile file,
                                         ModelMap modelMap,
                                         @AuthenticationPrincipal User user) throws IOException, ClassNotFoundException, DbException {
        int count = fileService.executeScript(file, user);
        HistoryOperationDTO historyOperationDTO = new HistoryOperationDTO();
        historyOperationDTO.setStatus(true);
        historyOperationDTO.setObjCount(count);

        historyOperationService.createHistoryOperation(historyOperationDTO, user);
        return new ResponseEntity<>("Скрипт успешно выполнен", HttpStatus.CREATED);
    }

}
