package com.company.repository;

import com.company.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository extends GenericRepository<Task> {
    public static final String TASKS_FILE_NAME = "tasks.txt";

    private final UserRepository userRepository = new UserRepository();

    public void addTask(Task task) {
        if (!userRepository.usernameExists(task.getUsername())) {
            throw new IllegalArgumentException("No such username.");
        }
        super.add(task);
    }

    //TODO Select by username, on a real database
    public List<Task> findAllByUsername(String username) {
        return super.findAll()
                .stream()
                .filter(task -> task.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public String getFileName() {
        return TASKS_FILE_NAME;
    }
}
