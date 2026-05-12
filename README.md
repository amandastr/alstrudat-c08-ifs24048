# alstrudat-c01-username

## Description
Sebuah rumah sakit memiliki dua jenis antrian pasien:
- Antrian IGD (untuk pasien darurat)
- Antrian Reguler (untuk pasien biasa)

Setiap pasien memiliki nama dan tingkat prioritas (1=paling parah, 5=ringan).
Pasien IGD selalu didahulukan dari pasien Reguler saat SERVE dipanggil.
Jika tidak ada pasien IGD, baru layani pasien Reguler.
Jika prioritas sama dalam satu antrian, yang lebih dulu datang didahulukan.

Sistem juga mendukung UNDO menggunakan Stack dan pencatatan statistik.

Operasi yang tersedia:
- ENQUEUE_IGD nama prioritas : tambah pasien ke antrian IGD
- ENQUEUE_REG nama prioritas : tambah pasien ke antrian Reguler
- SERVE : layani pasien (IGD didahulukan), simpan ke stack riwayat
- UNDO : batalkan pelayanan terakhir, kembalikan ke antrian asal
- PEEK : tampilkan pasien yang akan dilayani berikutnya
- STATS : tampilkan statistik berikut:
    Total dilayani: [jumlah]
    Rata-rata prioritas: [nilai dengan 2 desimal]
    Pasien IGD dilayani: [jumlah]
    Pasien Reguler dilayani: [jumlah]

Aturan output:
- SERVE berhasil (IGD) : [IGD] Dilayani: [nama] (Prioritas: [prioritas])
- SERVE berhasil (Reguler) : [REG] Dilayani: [nama] (Prioritas: [prioritas])
- SERVE gagal : Semua antrian kosong
- UNDO berhasil (IGD) : UNDO: [nama] dikembalikan ke antrian IGD
- UNDO berhasil (Reguler) : UNDO: [nama] dikembalikan ke antrian Reguler
- UNDO gagal : Tidak ada riwayat
- PEEK (IGD ada) : Berikutnya [IGD]: [nama] (Prioritas: [prioritas])
- PEEK (IGD kosong, REG ada) : Berikutnya [REG]: [nama] (Prioritas: [prioritas])
- PEEK (semua kosong) : Semua antrian kosong

Catatan STATS:
- UNDO tidak mengurangi statistik
- Rata-rata dihitung dari semua pasien yang pernah dilayani (termasuk yang di-UNDO)
- Jika belum ada yang dilayani: Total dilayani: 0, Rata-rata prioritas: 0.00

## Source Codes

| No | File | Deskripsi |
|----|------|-----------|
| 1 | App.java | Bawaan - membaca input dan memanggil Program |
| 2 | Program.java | Melengkapi Fungsi - logika antrian, stack, dan statistik |

## Test Cases

| No | Input | Output |
|----|-------|--------|
| 1 | 8<br/>ENQUEUE_IGD Budi 3<br/>ENQUEUE_REG Ani 1<br/>ENQUEUE_IGD Citra 1<br/>PEEK<br/>SERVE<br/>SERVE<br/>SERVE<br/>STATS | Berikutnya [IGD]: Citra (Prioritas: 1)<br/>[IGD] Dilayani: Citra (Prioritas: 1)<br/>[IGD] Dilayani: Budi (Prioritas: 3)<br/>[REG] Dilayani: Ani (Prioritas: 1)<br/>Total dilayani: 3<br/>Rata-rata prioritas: 1.67<br/>Pasien IGD dilayani: 2<br/>Pasien Reguler dilayani: 1 |
| 2 | 6<br/>ENQUEUE_REG Doni 2<br/>SERVE<br/>UNDO<br/>STATS<br/>SERVE<br/>STATS | [REG] Dilayani: Doni (Prioritas: 2)<br/>UNDO: Doni dikembalikan ke antrian Reguler<br/>Total dilayani: 1<br/>Rata-rata prioritas: 2.00<br/>Pasien IGD dilayani: 0<br/>Pasien Reguler dilayani: 1<br/>[REG] Dilayani: Doni (Prioritas: 2)<br/>Total dilayani: 2<br/>Rata-rata prioritas: 2.00<br/>Pasien IGD dilayani: 0<br/>Pasien Reguler dilayani: 2 |
| 3 | 4<br/>SERVE<br/>UNDO<br/>PEEK<br/>STATS | Semua antrian kosong<br/>Tidak ada riwayat<br/>Semua antrian kosong<br/>Total dilayani: 0<br/>Rata-rata prioritas: 0.00<br/>Pasien IGD dilayani: 0<br/>Pasien Reguler dilayani: 0 |
| 4 | 9<br/>ENQUEUE_IGD Rudi 2<br/>ENQUEUE_REG Sari 2<br/>ENQUEUE_IGD Tono 1<br/>SERVE<br/>UNDO<br/>SERVE<br/>SERVE<br/>PEEK<br/>STATS | [IGD] Dilayani: Tono (Prioritas: 1)<br/>UNDO: Tono dikembalikan ke antrian IGD<br/>[IGD] Dilayani: Tono (Prioritas: 1)<br/>[IGD] Dilayani: Rudi (Prioritas: 2)<br/>Berikutnya [REG]: Sari (Prioritas: 2)<br/>Total dilayani: 3<br/>Rata-rata prioritas: 1.33<br/>Pasien IGD dilayani: 2<br/>Pasien Reguler dilayani: 1 |

## Compile
`mvn clean package`

## Run
`mvn exec:java`