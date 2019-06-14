package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.CategoryRepository;
import kr.ac.jejunu.polls.dto.category.CategoryFindRequestDto;
import kr.ac.jejunu.polls.dto.category.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long save(CategorySaveRequestDto dto) {
        return categoryRepository.save(dto.toEntity()).getId();
    }

    public Category find(CategoryFindRequestDto dto) {
        return categoryRepository.findById(dto.getId()).get();
    }

    public void delete() {

    }
}
