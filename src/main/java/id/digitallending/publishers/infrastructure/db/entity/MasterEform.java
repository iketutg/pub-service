package id.digitallending.publishers.infrastructure.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasterEform extends BaseEntity{

    @Column
    private String nik;

    @Column
    private String nama;

    @Column
    private String npwp;

    @Column
    private String nohp;

    @Column
    private String email;

    @Column(name = "alamat_detail")
    private String alamat;

    @Column(name = "latitude_map")
    private String latitudeMap;

    @Column(name = "longitude_map")
    private String longitudeMap;

    @Column(name = "propinsi_id")
    private String propinsiId;

    @Column(name = "kabupaten_id")
    private String kabupatenId;

    @Column(name = "kecamatan_id")
    private String kecamatanId;

    @Column(name = "kelurahan_id")
    private String kelurahanId;

    @Column(name = "kode_pos")
    private String kodePos;

    @Column(name = "jenis_pekerjaan_id")
    private String jenisPekerjaanId;

    @Column(name = "jenis_instansi_id")
    private String jenisInstansiId;

    @Column(name = "cabang_pengajuan_id")
    private String cabangId;

    @Column
    private String lokasi_usaha;

    @Column
    private BigDecimal plafon;

    @Column
    private Integer tenor;

    @Column(name = "foto_ktp")
    private String fotoKtp;

    @Column(name="product_id")
    private String productId;

    @Column(name="registration_id")
    private String regId;

    @Column(name="ktp_file_name")
    private String ktpFileName;

    //Todo change String Json / BLOB JSON
    @Column(name="status")
    private String status;


}
