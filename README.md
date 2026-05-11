# alstrudat-c08-ifs24048

## Description
Sebuah rumah sakit memiliki sistem antrian pasien berbasis prioritas.
Pasien dengan tingkat keparahan lebih tinggi (prioritas lebih kecil) akan
dilayani lebih dulu. Sistem juga mendukung fitur UNDO menggunakan Stack
untuk membatalkan pelayanan terakhir.

Operasi yang tersedia:
- ENQUEUE nama prioritas : tambah pasien ke antrian
- DEQUEUE : layani pasien terdepan
- UNDO : batalkan pelayanan terakhir
- PEEK : lihat pasien berikutnya tanpa menghapus

## Source Codes

| No | File | Deskripsi |
|----|------|-----------|
| 1 | App.java | Bawaan - membaca input dan memanggil Program |
| 2 | Program.java | Melengkapi Fungsi - logika antrian & stack |

## Test Cases

| No | Input | Output |
|----|-------|--------|
| 1 | 6\nENQUEUE Budi 3\nENQUEUE Ani 1\nENQUEUE Citra 2\nPEEK\nDEQUEUE\nDEQUEUE | Berikutnya: Ani (Prioritas: 1)\nDilayani: Ani (Prioritas: 1)\nDilayani: Citra (Prioritas: 2) |
| 2 | 5\nENQUEUE Doni 2\nDEQUEUE\nUNDO\nPEEK\nDEQUEUE | Dilayani: Doni (Prioritas: 2)\nUNDO: Doni dikembalikan ke antrian\nBerikutnya: Doni (Prioritas: 2)\nDilayani: Doni (Prioritas: 2) |

## Compile
`mvn clean package`

## Run
`mvn exec:java`