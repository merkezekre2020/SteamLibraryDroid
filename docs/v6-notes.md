# V6 Notes

Bu aşamada dependency wiring temeli eklendi.

Eklenenler:
- `SteamLibraryApplication`
- `DatabaseProvider`
- `GetCachedLibraryUseCase`
- `SaveGameEntryUseCase`
- `AppContainer` artık init ile context alacak şekilde güncellenecek

Sonraki iş:
- gerçek coroutine scope ve suspend UI bağlama
- ViewModel'leri AndroidX ViewModel'e taşıma
- fake repo kullanımını kademeli azaltma
