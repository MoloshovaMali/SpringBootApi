package peaksoft.springbootapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootapi.dto.CompanyRequest;
import peaksoft.springbootapi.dto.CompanyResponse;
import peaksoft.springbootapi.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping

    public List<CompanyResponse>getAll(){
        return companyService.getAllCompanies();
    }
    @GetMapping("{id}")
    public CompanyResponse getCompany(@PathVariable ("id")Long id){
        return companyService.getCompanyById(id);
    }
    @PostMapping
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest){
        return companyService.saveCompany(companyRequest);
    }
    @PutMapping("{id}")
    public CompanyResponse update(@PathVariable ("id")Long id,@RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(id,companyRequest);
    }
    @DeleteMapping("{id}")
    private String  delete(@PathVariable("id")Long id){
        companyService.deleterCompany(id);
        return "Successfully deleted Company with id: "+id;
    }
}
