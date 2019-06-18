package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.CategoryRepository;
import kr.ac.jejunu.polls.dto.category.CategoryFindRequestDto;
import kr.ac.jejunu.polls.dto.category.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category save(CategorySaveRequestDto dto) {
        Category category = categoryRepository.save(dto.toEntity());
        return category;
    }

    public Category find(CategoryFindRequestDto dto) {
        return categoryRepository.findById(dto.getId()).get();
    }

    public List<Category> read() {
        return categoryRepository.findAll();
    }
    public void delete() {

    }
}
