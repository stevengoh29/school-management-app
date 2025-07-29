package io.stevengoh.portfolio.school_management_app.modules.users;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.SearchUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.modules.users.mappers.UserRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.users.mappers.UserResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserRequestMapper requestMapper;
    private final UserResponseMapper responseMapper;

    private final PasswordEncoder passwordEncoder; // <-- Add this

    private final InstitutionService institutionService;

    @Override
    public User findByUuidOrNull(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public User findByUuidOrThrow(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public PaginatedResult<SimpleResUserDto> getUsers(SearchUserDto searchDto, Pageable pageable) {
        SpecificationBuilder<User, SearchUserDto> builder = new SpecificationBuilder<>();
        Specification<User> spec = builder.build(searchDto);

        Page<User> pageResult = userRepository.findAll(spec, pageable);
        List<SimpleResUserDto> dtoList = responseMapper.toDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResUserDto getUserByUuidOrNull(UUID uuid) {
        User user = this.findByUuidOrNull(uuid);
        return responseMapper.toDetailedDto(user);
    }

    @Override
    public DetailedResUserDto getUserByUuidOrThrow(UUID uuid) {
        User user = this.findByUuidOrThrow(uuid);
        return responseMapper.toDetailedDto(user);
    }

    @Override
    public DetailedResUserDto createUser(CreateUserDto createUserDto) {
        Institution institution = institutionService.findByUuidOrThrow(createUserDto.getInstitutionUuid());

        User user = requestMapper.createEntityToDto(createUserDto);

        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user.setInstitution(institution);

        User result = userRepository.save(user);
        return responseMapper.toDetailedDto(result);
    }

    @Override
    public DetailedResUserDto updateUser(UUID uuid, UpdateUserDto updateUserDto) {
        User user = this.findByUuidOrThrow(uuid);
        requestMapper.updateEntityToDto(updateUserDto, user);

        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User result = userRepository.save(user);
        return responseMapper.toDetailedDto(result);
    }

    @Override
    public void deleteUser(UUID uuid) {
        User user = this.findByUuidOrThrow(uuid);

        user.setStatus(UserStatus.INACTIVE);
        user.setDeletedAt(LocalDateTime.now());
        user.setDeletedBy("UNKNOWN");

        userRepository.save(user);
    }
}