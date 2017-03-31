package gorm

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // tag::graphql[]
        post "/graphql"(controller: "graph")
        // end::graphql[]

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
