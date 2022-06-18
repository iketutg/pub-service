package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;

public interface CekStatusPengajuanUseCase {
    GeneralResponseDto cekStatus(String PhoneNumber);
}
