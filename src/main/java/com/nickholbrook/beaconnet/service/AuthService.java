package com.nickholbrook.beaconnet.service;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.UserType;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	/*clientId = 2jkihs1a8su8n4jq0lvihsh3po
			userPoolId = us-east-1_3vocxnITQ
			endpoint = cognito-idp.us-east-1.amazonaws.com
			region = us-east-1
	identityPoolId = us-east-1:f2810be3-a906-4a1e-83bc-aa1230b6789

	public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
		ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider =
				new ClasspathPropertiesFileCredentialsProvider();

		return AWSCognitoIdentityProviderClientBuilder.standard()
				.withCredentials(propertiesFileCredentialsProvider)
				.withRegion(cognitoConfig.getRegion())
				.build();

	}

	public UserType signUp(UserSignUpRequest signUpRequest){

		AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
		AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
				.withUserPoolId(cognitoConfig.getUserPoolId())
				.withUsername(signUpRequest.getUsername())
		withUserAttributes(
				new AttributeType()
						.withValue(signUpRequest.getEmail()),
				new AttributeType()
						.withName("name")
						.withValue(signUpRequest.getName()),
				new AttributeType()
						.withName("family_name")
						.withValue(signUpRequest.getLastname()),
				new AttributeType()
						.withName("phone_number")
						.withValue(signUpRequest.getPhoneNumber()),
				new AttributeType()
						.withName("custom:companyName")
						.withValue(signUpRequest.getCompanyName()),
				new AttributeType()
						.withName("custom:companyPosition")
						.withValue(signUpRequest.getCompanyPosition()),
				new AttributeType()
						.withName("email_verified")
						.withValue("true"))
				.withTemporaryPassword("TEMPORARY_PASSWORD")))
              .withMessageAction("SUPPRESS")
				.withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
				.withForceAliasCreation(Boolean.FALSE);

		AdminCreateUserResult createUserResult =  cognitoClient.adminCreateUser(cognitoRequest);
		UserType cognitoUser =  createUserResult.getUser();

		return cognitoUser;

	}*/
}
