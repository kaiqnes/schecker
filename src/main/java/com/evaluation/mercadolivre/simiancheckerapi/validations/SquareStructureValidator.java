package com.evaluation.mercadolivre.simiancheckerapi.validations;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.evaluation.mercadolivre.simiancheckerapi.models.SampleRequest;

public class SquareStructureValidator implements ConstraintValidator<SquareStructure, SampleRequest> {

	@Override
	public boolean isValid(SampleRequest sample, ConstraintValidatorContext context) {
		return Arrays.asList(sample.getDna()).stream().allMatch(n -> n.length() == sample.getDna().length);
	}
}
