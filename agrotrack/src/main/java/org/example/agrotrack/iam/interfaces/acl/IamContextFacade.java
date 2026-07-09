package org.example.agrotrack.iam.interfaces.acl;

import java.util.Optional;

/**
 * ACL facade that exposes IAM capabilities (credential provisioning) to other bounded contexts,
 * without leaking IAM's internal aggregates/commands.
 */
public interface IamContextFacade {

    /**
     * Creates a new IAM credential.
     *
     * @param email        the email that will act as login identifier
     * @param rawPassword  the raw password to hash and store
     * @param roleName     the {@code Roles} enum constant name to assign (e.g. {@code "ROLE_FARMER"})
     * @return the created IAM user id, or empty if the email is already registered or the role is invalid
     */
    Optional<String> createUser(String email, String rawPassword, String roleName);

    /**
     * Checks whether an IAM credential with the given id exists.
     *
     * @param iamUserId the IAM user id to look up
     * @return {@code true} if a credential with that id exists
     */
    boolean existsById(String iamUserId);

    /**
     * Resolves the IAM user id for a given email.
     *
     * @param email the email to look up
     * @return the IAM user id, or empty if no credential exists for that email
     */
    Optional<String> findIdByEmail(String email);

    /**
     * Updates the email/password of an existing IAM credential.
     *
     * @param iamUserId   the IAM user id to update
     * @param email       the new email
     * @param rawPassword the new raw password to hash and store
     * @return {@code true} if the credential was updated successfully
     */
    boolean updateCredentials(String iamUserId, String email, String rawPassword);
}
