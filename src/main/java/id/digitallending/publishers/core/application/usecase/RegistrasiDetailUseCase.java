package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiEformDto;

public interface RegistrasiDetailUseCase {
    GeneralResponseDto registrasiDetail(RegistrasiEformDto registrasiEformDto);
}
