package com.assignment.configuration;

import com.assignment.model.Assignment;
import com.assignment.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class AssignmentModelListener extends AbstractMongoEventListener<Assignment> {
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Assignment> event) {
        event.getSource().setAssignmentId(sequenceGeneratorService.generateSequence(Assignment.SEQUENCE_NAME));
    }
}
