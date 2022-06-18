package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CekStatusPengajuanUseCaseImpl implements CekStatusPengajuanUseCase{
    @Override
    public GeneralResponseDto cekStatus(String phoneNumber) {
        //Todo get Data from Database
        //Todo Change response to Struct Json
        System.out.println(phoneNumber);
        GeneralResponseDto generalResponseDto = new GeneralResponseDto();
        generalResponseDto.setResCode(200);
        generalResponseDto.setMessage("Success , Registrasi On Process");
        generalResponseDto.setRefNum("1234");
        return generalResponseDto;
    }
}
