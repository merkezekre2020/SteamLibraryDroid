# V5 Notes

Bu aşamada proje için Room tabanlı local cache taslağı eklendi.

Eklenenler:
- entity'ler
- dao'lar
- database sınıfı
- mapper'lar
- `LibraryRepositoryV2`
- `SyncLibraryUseCase`

Henüz eksik olanlar:
- gerçek Room instance oluşturma
- repository injection
- UI katmanının suspend/async akışa bağlanması
- PocketBase kayıtlarını gerçek veri akışına bağlama
