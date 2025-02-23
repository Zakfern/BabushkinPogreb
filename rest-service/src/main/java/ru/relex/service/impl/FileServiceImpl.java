package ru.relex.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.relex.dao.AppDocumentDAO;
import ru.relex.dao.AppPhotoDAO;
import ru.relex.entity.AppDocument;
import ru.relex.entity.AppPhoto;
import ru.relex.service.FileService;
import ru.relex.utils.CryptoTool;

@Log4j
@Service
public class FileServiceImpl implements FileService {
    private final AppDocumentDAO appDocumentDAO;
    private final AppPhotoDAO appPhotoDAO;
    private final CryptoTool cryptoTool;

    public FileServiceImpl(AppDocumentDAO appDocumentDAO, AppPhotoDAO appPhotoDAO, CryptoTool cryptoTool) {
	this.appDocumentDAO = appDocumentDAO;
	this.appPhotoDAO = appPhotoDAO;
	this.cryptoTool = cryptoTool;
    }

    @Override
    public AppDocument getDocument(String hash) {
        var id = cryptoTool.idOf(hash);
        if (id == null) {
            return null;
	}
	return appDocumentDAO.findById(id).orElse(null);
    }

    @Override
    public AppPhoto getPhoto(String hash) {
	var id = cryptoTool.idOf(hash);
	if (id == null) {
	    return null;
	}
	return appPhotoDAO.findById(id).orElse(null);
    }
}
