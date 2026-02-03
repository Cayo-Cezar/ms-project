package com.cz.hr_worker.resources;

import com.cz.hr_worker.entities.Worker;
import com.cz.hr_worker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResoucer {

    private static final Logger logger = LoggerFactory.getLogger(WorkerResoucer.class);

    @Autowired
    private Environment env;

    @Autowired
    private WorkerRepository repository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT = " + env.getProperty("local.server.port"));
        Worker obj = repository.findById(id).orElseThrow();
        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        //logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }
}
