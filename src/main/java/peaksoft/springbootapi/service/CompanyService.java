package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.CompanyRequest;
import peaksoft.springbootapi.dto.CompanyResponse;
import peaksoft.springbootapi.dto.CompanyResponseView;
import peaksoft.springbootapi.entity.Company;
import peaksoft.springbootapi.repository.CompanyRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<CompanyResponse> getAllCompanies() {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            companyResponses.add(mapToResponse(company));
        }
        return companyResponses;
    }

    public CompanyResponse getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        return mapToResponse(company);
    }

    public CompanyResponse saveCompany(CompanyRequest request) {
        Company company = mapToEntity(request);
        companyRepository.save(company);
        return mapToResponse(company);
    }

    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        Company company = companyRepository.findById(id).get();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setDirectorName(request.getDirectorName());
        companyRepository.save(company);
        return mapToResponse(company);

    }

    public void deleterCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    public Company mapToEntity(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setDirectorName(request.getDirectorName());
        company.setLocalDate(LocalDate.now());
        return company;
    }

    public CompanyResponse mapToResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setCompanyName(company.getCompanyName());
        response.setLocatedCountry(company.getLocatedCountry());
        response.setDirectorName(company.getDirectorName());
        response.setLocalDate(company.getLocalDate());
        return response;
    }

    public CompanyResponseView searchAndPagination(String text, int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, size);//1
        CompanyResponseView companyResponseView = new CompanyResponseView();
        companyResponseView.setCompanyResponses(view(search(text,pageable)));
        return companyResponseView;
    }

    public List<CompanyResponse> view(List<Company> companies) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company : companies) {
            companyResponses.add(mapToResponse(company));
        }
        return companyResponses;
    }


    private List<Company> search(String text, Pageable pageable) {
        String name = text == null ? "" : text;
        return companyRepository.searchAndPagination(name.toUpperCase(),pageable);
    }
}
