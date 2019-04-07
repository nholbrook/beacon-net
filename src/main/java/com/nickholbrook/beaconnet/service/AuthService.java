package com.nickholbrook.beaconnet.service;

import java.util.List;
import java.util.Map;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.nickholbrook.beaconnet.model.UserResponse;
import com.nickholbrook.beaconnet.util.LoadFromProperties;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	/*clientId = 2jkihs1a8su8n4jq0lvihsh3po
			userPoolId = us-east-1_3vocxnITQ
			endpoint = cognito-idp.us-east-1.amazonaws.com
			region = us-east-1
	identityPoolId = us-east-1:f2810be3-a906-4a1e-83bc-aa1230b6789*/

    final static Regions REGION;
    final static String ENDPOINT;
    final static String USER_POOL_ID;
    final static String CLIENT_ID;
    final static String IDENTITY_POOL_ID;

    static {
        Map<String, String> config = LoadFromProperties.loadFromProperties();

        ENDPOINT = LoadFromProperties.fetchValue(config, "cognito-idp.us-east-1.amazonaws.com");
        USER_POOL_ID = LoadFromProperties.fetchValue(config, "us-east-1_3vocxnITQ");
        CLIENT_ID = LoadFromProperties.fetchValue(config, "2jkihs1a8su8n4jq0lvihsh3po");
        IDENTITY_POOL_ID = LoadFromProperties.fetchValue(config, "us-east-1:f2810be3-a906-4a1e-83bc-aa1230b6789");
        REGION = Regions.valueOf(LoadFromProperties.fetchValue(config, "us-east-1"));

    }

    AuthService() {
        System.out.println(getUserInfo("sboucher").toString());
    }

    public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider =
                new ClasspathPropertiesFileCredentialsProvider();

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(propertiesFileCredentialsProvider)
                .withRegion(REGION)
                .build();

    }

    public UserResponse getUserInfo(String username) {

        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        AdminGetUserRequest userRequest = new AdminGetUserRequest()
                .withUsername(username)
                .withUserPoolId(USER_POOL_ID);


        AdminGetUserResult userResult = cognitoClient.adminGetUser(userRequest);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(userResult.getUsername());
        userResponse.setUserStatus(userResult.getUserStatus());
        userResponse.setUserCreateDate(userResult.getUserCreateDate());
        userResponse.setLastModifiedDate(userResult.getUserLastModifiedDate());

        List<AttributeType> userAttributes = userResult.getUserAttributes();
        for (AttributeType attribute : userAttributes) {
            if (attribute.getName().equals("custom:companyName")) {
                userResponse.setCompanyName(attribute.getValue());
            } else if (attribute.getName().equals("custom:companyPosition")) {
                userResponse.setCompanyPosition(attribute.getValue());
            } else if (attribute.getName().equals("email")) {
                userResponse.setEmail(attribute.getValue());
            }
        }

        cognitoClient.shutdown();
        return userResponse;
    }

}
