/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.systemtest.resources.kubernetes;

import io.fabric8.kubernetes.api.model.rbac.ClusterRole;
import io.strimzi.systemtest.Constants;
import io.strimzi.systemtest.resources.ResourceType;
import io.strimzi.test.k8s.KubeClusterResource;

import static io.strimzi.test.k8s.KubeClusterResource.kubeClient;

public class ClusterRoleResource implements ResourceType<ClusterRole> {

    @Override
    public String getKind() {
        return Constants.CLUSTER_ROLE;
    }
    @Override
    public ClusterRole get(String namespace, String name) {
        return kubeClient("default").getClusterRole(name);
    }
    @Override
    public void create(ClusterRole resource) {
        // ClusterRole his operation namespace is only 'default'
        resource.getMetadata().setNamespace(KubeClusterResource.getInstance().defaultNamespace());
        kubeClient().createOrReplaceClusterRoles(resource);
    }
    @Override
    public void delete(ClusterRole resource) {
        // ClusterRole his operation namespace is only 'default'
        resource.getMetadata().setNamespace(KubeClusterResource.getInstance().defaultNamespace());
        kubeClient().deleteClusterRole(resource);
    }
    @Override
    public boolean waitForReadiness(ClusterRole resource) {
        return resource != null;
    }
}
