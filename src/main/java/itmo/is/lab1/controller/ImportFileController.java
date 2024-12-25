package itmo.is.lab1.controller;

import io.minio.errors.*;
import itmo.is.lab1.DTO.model.additional.HistoryOperationDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.ImportFormatException;
import itmo.is.lab1.exceptionHandler.MinIOConnectException;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.additional.HistoryOperationService;
import itmo.is.lab1.service.file.FileService;
import itmo.is.lab1.service.file.MinIOService;
import org.apache.commons.compress.utils.IOUtils;
import org.postgresql.PGConnection;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/import")
@Validated
public class ImportFileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private HistoryOperationService historyOperationService;
    @Autowired
    private MinIOService minIOService;

    @PostMapping
    public ResponseEntity<String> submit(@RequestParam("file") MultipartFile file,
                                         ModelMap modelMap,
                                         @AuthenticationPrincipal User user) throws IOException, ClassNotFoundException, DbException, ImportFormatException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MinIOConnectException {
        int count;
        try {
            count = fileService.executeScript(file, user);
        } catch (DataAccessResourceFailureException e) {
            throw new DbException("Ошибка подключения к базе данных");
        } catch (ConnectException e) {
            throw new MinIOConnectException();
        }
        HistoryOperationDTO historyOperationDTO = new HistoryOperationDTO();
        historyOperationDTO.setStatus(true);
        historyOperationDTO.setObjCount(count);
        historyOperationDTO.setFilename(file.getOriginalFilename());

        historyOperationService.createHistoryOperation(historyOperationDTO, user);
        return new ResponseEntity<>("Скрипт успешно выполнен", HttpStatus.CREATED);
    }

    @GetMapping("/{filename}")
    public @ResponseBody byte[] getFileByName(@PathVariable String filename, @AuthenticationPrincipal User user) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minIOService.getObjectFromBucket(user.getUsername() + '_' + filename).readAllBytes();
    }

    @GetMapping("/admin/{filename}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // admin need to concrete filename in format "<username>_<filename>"
    public @ResponseBody byte[] getFileByNameForAdmin(@PathVariable String filename) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minIOService.getObjectFromBucket(filename).readAllBytes();
    }


}
