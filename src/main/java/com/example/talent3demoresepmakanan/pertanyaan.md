### 1. Level Controller 
```java
@GetMapping("/get/{id}")// 
public GetLevelsResponseDTO getLevel(@PathVariable Long id){
  GetLevelsRequestDTO req = new GetLevelsRequestDTO();
  req.setId(id);
  return levelService.getLevel(req);
}
```
- `@GetMapping("/get/{id}")` bagaimana jika parameternya nama, atau lainnya ?
- `@PathVariable Long id` kenapa tidak langsung menggunakan response saja ?

```java
   @GetMapping("/getByName")
    public GetListLevelsResponseDTO getLevelByname(@RequestBody GetLevelsRequestDTO req){
        req.setLevelsName(req.getLevelsName());
        return levelService.getCategoryByName(req);
    }
```
- `req.setLevelsName(req.getLevelsName());` kenapa harus menggunakan get set terlebih dahulu, tidak langsung saja ?

```java

 public GetLevelsResponseDTO getLevel(GetLevelsRequestDTO req) {
        Optional<Levels> levelOptional = levelsRepository.findById(req.getId());
        if(levelOptional.isPresent()){
            GetLevelsResponseDTO levelsDTO = objectMapper.convertValue(levelOptional.get(),GetLevelsResponseDTO.class);
            return levelsDTO;
        }else{
            return GetLevelsResponseDTO.builder().build();
        }
    }

 public GetListLevelsResponseDTO getLevelByName(GetLevelsRequestDTO req) {
         List<Levels> level = levelsRepository.findByLevelsName(req.getLevelsName());
         List<GetLevelsResponseDTO> levelsDTO = level.stream()
         .map(l-> objectMapper.convertValue(l,GetLevelsResponseDTO.class))
         .collect(Collectors.toList());

         return GetListLevelsResponseDTO.builder()
         .levels(levelsDTO)
         .build();
}
```

- apa perbedaan antara menggunakan object mapper dengan builder ?