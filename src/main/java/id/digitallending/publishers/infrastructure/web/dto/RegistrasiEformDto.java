package id.digitallending.publishers.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"@class"})
public class RegistrasiEformDto {

    @NotNull(message = "The jenisPekerjaan is required.")
    @NotEmpty(message = "jenisPekerjaan Not Empty")
    @Size(min = 8, message = "jenisPekerjaan should have at least 8 characters ")
    private String jenisPekerjaan;

    @NotNull(message = "The jenisInstansi is required.")
    @NotEmpty(message = "jenisInstansi Not Empty")
    @Size(min = 8, message = "jenisInstansi should have at least 8 characters ")
    private String jenisInstansi;

    @NotNull(message = "The cabangPengajuan is required.")
    @NotEmpty(message = "cabangPengajuan Not Empty")
    @Size(min = 4, message = "cabangPengajuan should have at least 8 characters ")
    private String cabangPengajuan;

    @NotNull(message = "The cabangPengajuan is required.")
    @NotEmpty(message = "cabangPengajuan Not Empty")
    @Size(min = 8, message = "cabangPengajuan should have at least 8 characters ")
    private String lokasiUsaha;

    @NotNull(message = "The plafon is required.")
    @Min(value = 1, message = "The plafon of bags must be greater than 0")
    private float plafon;

    @NotNull(message = "The tenor is required.")
    @Min(value = 1, message = "The tenor of bags must be greater than 0")
    private Integer tenor;

    @NotNull(message = "The fotoKtp is required.")
    @NotEmpty(message = "fotoKtp Not Empty")
    @Size(min = 64, message = "fotoKtp should have at least 8 characters ")
    private String fotoKtp;

    @NotNull(message = "The jenisProduk is required.")
    @NotEmpty(message = "jenisProduk Not Empty")
    @Size(min = 64, message = "jenisProduk should have at least 8 characters ")
    private String jenisProduk;

    @NotNull(message = "The kodeAplikasi is required.")
    @NotEmpty(message = "kodeAplikasi Not Empty")
    @Size(min = 4, message = "kodeAplikasi should have at least 8 characters ")
    private String kodeAplikasi;

    @NotNull(message = "The refNumber is required.")
    @Size(min = 8, message = "refNumber should have at least 8 characters ")
    private String refNumber;

}
